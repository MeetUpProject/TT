document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("writeForm");

    form.addEventListener("submit", function (e) {
        e.preventDefault(); // 기본 form 전송 방지

        const title = document.getElementById("title").value;
        const content = document.getElementById("content").value;
        const writer = document.getElementById("writer").value;
        const password = document.getElementById("password").value;

        if (!title || !content || !writer || !password) {
            alert("모든 항목을 입력해주세요.");
            return;
        }

        const postData = {
            title,
            content,
            writer,
            password
        };

        fetch("/posts", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(postData)
        })
            .then(response => {
                if (response.ok) {
                    alert("게시글이 등록되었습니다.");
                    window.location.href = "/post_list.html"; // 목록 페이지로 이동
                } else {
                    alert("게시글 등록 실패");
                }
            })
            .catch(err => alert("오류 발생: " + err.message));
    });
});
