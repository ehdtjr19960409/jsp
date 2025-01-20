/**
 * 회원 가입 폼 관련 JS
 */

// 메시지와 입력 요소 변수 선언
let $checkMsg = $("#check-id-msg"); // 아이디 입력 검증 메시지 출력 요소
let $checkPwMsg1 = $("#check-pw-msg1"); // 비밀번호 검증 메시지 출력 요소
let $checkPwMsg2 = $("#check-pw-msg2"); // 비밀번호 검증 메시지 출력 요소
let $idInput = $("#id"); // 아이디 입력 필드
let $pwInput = $("#password"); // 비밀번호 입력 필드
/*
// 아이디 입력 유효성 검증
$idInput.on('blur', function () {
    if ($(this).val() === '') {
        // 입력값이 비어 있으면 메시지 표시
        $checkMsg.text('아이디를 입력하세요!');
    } else {
        $checkMsg.text(''); // 입력값이 있으면 메시지 초기화
    }
});

// 아이디 중복 검사를 위한 fetch 요청
let checkId = function () {
    fetch('/member/checkIdOk.me?memberId=' + encodeURIComponent($idInput.val()), {
        method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP 오류! 상태 코드: ${response.status}`);
        }
        return response.json(); // 서버에서 JSON 형식의 응답을 받음
    })
    .then(result => {
        console.log(result); // 서버 응답 결과 확인 (개발자 도구에서)
        if (result.available) {
            $checkMsg.text('사용 가능한 아이디입니다.');
        } else {
            $checkMsg.text('이미 사용 중인 아이디입니다.');
        }
    })
    .catch(error => {
        console.error('오류 발생:', error);
        $checkMsg.text('아이디 중복 검사 중 오류가 발생했습니다.');
    });
};

// 아이디 필드 값 변경 시 중복 검사 실행
$idInput.on('change', checkId);*/

//id 중복검사
let checkId = function () {
    fetch('/member/checkIdOk.me?memberId=' + encodeURIComponent($idInput.val()), {
        method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP 오류! 상태 코드: ${response.status}`);
        }
        return response.json(); // 서버에서 JSON 형식의 응답을 받음
    })
    .then(result => {
        console.log(result); // 서버 응답 결과 확인
        if (result.available) {
            $checkMsg.text('사용 가능한 아이디입니다.');
        } else {
            $checkMsg.text('이미 사용 중인 아이디입니다.');
        }
    })
    .catch(error => {
        console.error('오류 발생:', error);
        $checkMsg.text('아이디 중복 검사 중 오류가 발생했습니다.');
    });
};

// 아이디 필드 값 변경 시 중복 검사 실행
$idInput.on('change', checkId);

// 비밀번호 유효성 검사 (영문, 숫자, 특수문자 포함 8자 이상)
const regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[a-zA-Z\d!@#$%^&*()_+]{8,}$/;

$pwInput.on('blur', function() {
    if (regex.test($pwInput.val())) {
        // 비밀번호가 유효한 경우
        $checkPwMsg.text("사용 가능한 비밀번호입니다.");
    } else {
        // 비밀번호가 유효하지 않은 경우
        $checkPwMsg.html("사용 불가능한 비밀번호입니다.<br>영문, 숫자, 특수문자를 포함하여 8자 이상 입력하세요.");
    }
});

// 약관 동의 여부 확인
$('form').on('submit', function(e) {
    e.preventDefault(); // 기본 제출 이벤트 차단

    if ($('#agree').prop('checked')) {
        // 약관에 동의한 경우 폼 제출
        this.submit();
    } else {
        // 약관에 동의하지 않은 경우 경고 메시지 표시
        alert('약관에 동의해주세요!');
    }
});

// 핸드폰 번호 인증 처리
$("#sendSMSBtn").on("click", function() {
    const phoneNumber = $("#phoneNumber").val();

    if (!phoneNumber) {
        alert("핸드폰 번호를 입력해주세요.");
        return;
    }

    // 서버로 인증번호 요청
    fetch('/sendSMS', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ phoneNumber: phoneNumber })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP 오류! 상태 코드: ${response.status}`);
        }
        return response.json(); // JSON 응답 받음
    })
    .then(result => {
        if (result.success) {
            $("#sms-status").text("인증번호가 발송되었습니다. 메시지를 확인해주세요.");
        } else {
            $("#sms-status").text("인증번호 발송에 실패했습니다. 다시 시도해주세요.");
        }
    })
    .catch(error => {
        console.error('SMS 발송 오류:', error);
        $("#sms-status").text("인증번호 발송 중 오류가 발생했습니다.");
    });
});

// 인증번호 검증
$("#verificationCode").on("blur", function() {
    const verificationCode = $(this).val();

    if (!verificationCode) {
        $("#verification-status").text("인증번호를 입력해주세요.");
        return;
    }

    fetch('/verifyCode', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ code: verificationCode })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP 오류! 상태 코드: ${response.status}`);
        }
        return response.json();
    })
    .then(result => {
        if (result.success) {
            $("#verification-status").text("인증에 성공했습니다.");
        } else {
            $("#verification-status").text("인증번호가 일치하지 않습니다.");
        }
    })
    .catch(error => {
        console.error('인증 오류:', error);
        $("#verification-status").text("인증 처리 중 오류가 발생했습니다.");
    });
});
