document.addEventListener("DOMContentLoaded", function () {
    const idInput = document.getElementById("id");
    const passwordInput = document.getElementById("password");
    const passwordConfirmInput = document.getElementById("passwordConfirm");
    const sendSMSBtn = document.getElementById("sendSMSBtn");
    const phoneNumberInput = document.getElementById("phoneNumber");
    const verificationCodeInput = document.getElementById("verificationCode");
    const agreeCheckbox = document.getElementById("agree");

    const checkIdMsg = document.getElementById("check-id-msg");
    const checkPwMsg = document.getElementById("check-pw-msg");
    const checkPwConfirmMsg = document.getElementById("check-pw-confirm-msg");
    const verificationStatus = document.getElementById("verification-status" );

    // 아이디 중복 검사
    idInput.addEventListener("change", function () {
        const memberId = idInput.value.trim();
        if (memberId === "") {
            checkIdMsg.textContent = "아이디를 입력해주세요.";
            checkIdMsg.style.color = "red";
            return;
        }

        fetch(`/member/checkIdOk.me?memberId=${encodeURIComponent(memberId)}`)
            .then(response => {
                if (!response.ok) throw new Error(`HTTP 오류! 상태 코드: ${response.status}`);
                return response.json();
            })
            .then(data => {
                if (data.available) {
                    checkIdMsg.textContent = "사용 가능한 아이디입니다.";
                    checkIdMsg.style.color = "green";
                } else {
                    checkIdMsg.textContent = "이미 사용 중인 아이디입니다.";
                    checkIdMsg.style.color = "red";
                }
            })
            .catch(error => {
                console.error("아이디 중복 검사 오류:", error);
                checkIdMsg.textContent = "아이디 중복 검사 중 오류가 발생했습니다.";
                checkIdMsg.style.color = "red";
            });
    });

    // 비밀번호 유효성 검사
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;

    passwordInput.addEventListener("blur", function () {
        const password = passwordInput.value.trim();
        if (passwordRegex.test(password)) {
            checkPwMsg.textContent = "사용 가능한 비밀번호입니다.";
            checkPwMsg.style.color = "green";
        } else {
            checkPwMsg.textContent = "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상 입력해야 합니다.";
            checkPwMsg.style.color = "red";
        }
    });

    passwordConfirmInput.addEventListener("blur", function () {
        const password = passwordInput.value.trim();
        const confirmPassword = passwordConfirmInput.value.trim();

        if (password === confirmPassword && password !== "") {
            checkPwConfirmMsg.textContent = "비밀번호가 일치합니다.";
            checkPwConfirmMsg.style.color = "green";
        } else {
            checkPwConfirmMsg.textContent = "비밀번호가 일치하지 않습니다.";
            checkPwConfirmMsg.style.color = "red";
        }
    });

    // SMS 인증번호 발송
    sendSMSBtn.addEventListener("click", function () {
        const phoneNumber = phoneNumberInput.value.trim();
        if (phoneNumber === "") {
            alert("핸드폰 번호를 입력해주세요.");
            return;
        }

        fetch("/member/sendSMS.me", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ phoneNumber: phoneNumber })
        })
         .then(response => {
                 if (!response.ok) throw new Error(`HTTP 오류! 상태 코드: ${response.status}`);
             })
         .then(() => {
            console.log("발송 성공");
            console.log(verificationCodeInput.disabled);
            verificationCodeInput.disabled = false;
            console.log(verificationCodeInput.disabled);
            
            
         })
            .catch(error => {
                console.error("SMS 발송 오류:", error);
                alert("인증번호 발송 중 오류가 발생했습니다.");
            });
    });

    // 인증번호 확인
    verificationCodeInput.addEventListener("blur", function () {
        const verificationCode = verificationCodeInput.value.trim();
        if (verificationCode === "") {
            verificationStatus.textContent = "인증번호를 입력해주세요."; 
            verificationStatus.style.color = "red";
            return;
        }

        fetch("/member/verifyCode.me", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ code: verificationCode })
        })
            .then(response => {
                if (!response.ok) throw new Error(`HTTP 오류! 상태 코드: ${response.status}`);
                return response.json();
            })
            .then(data => {
            console.log(data);
                if (data.success) {
                    verificationStatus.textContent = "인증에 성공했습니다.";
                    verificationStatus.style.color = "green";
                } else {
                    verificationStatus.textContent = "인증번호가 일치하지 않습니다.";
                    verificationStatus.style.color = "red";
                }
            })
            .catch(error => {
                console.error("인증 확인 오류:", error);
                verificationStatus.textContent = "인증 처리 중 오류가 발생했습니다.";
                verificationStatus.style.color = "red";
            });
    });

    // 폼 제출 전 약관 확인
    document.querySelector("form").addEventListener("submit", function (e) {
        if (!agreeCheckbox.checked) {
            e.preventDefault();
            alert("약관에 동의해주세요.");
        }
    });
});



/**
 * 회원 가입 폼 관련 JS
 */

// 메시지와 입력 요소 변수 선언
/*let $checkMsg = $("#check-id-msg"); // 아이디 입력 검증 메시지 출력 요소
let $checkPwMsg1 = $("#check-pw-msg1"); // 비밀번호 검증 메시지 출력 요소
let $checkPwMsg2 = $("#check-pw-msg2"); // 비밀번호 검증 메시지 출력 요소
let $idInput = $("#id"); // 아이디 입력 필드
let $pwInput = $("#password"); // 비밀번호 입력 필드

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
$idInput.on('change', checkId);

//id 중복검사
let checkId = function () {
    fetch('/member/checkIdOk.me?memberId=' + encodeURIComponent($idInput.val()), {
		실제 url 변경x - 비동기 처리 
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
	//blur -> 해당 focus를 잃었을 때 
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
    fetch('/member/sendSMS.me', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ phoneNumber: phoneNumber })
		// JSON.stringfy -> js객체를 json문자열로 변환하여 서버에 전송하기 위함(서버에서 json 데이터를 받도록 설계된 경우 필요)
		// json과 js와의 차이 : 
		// JS는 프로그래밍 로직, 스크립트 언어 (객체OBJECT) 코드 실행 가능 함수 포함 가능
		// json은 텍스트 (String), 데이터 저장, API 통신
		
		// JSON을 JavaScript에서 사용하려면 JSON.Parse() 로 변환
		// JavaScript 객체를 JSON으로 내보내려면 JSON.stringfy() 사용
		
		
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

    fetch('/member/verifyCode', {
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
*/

