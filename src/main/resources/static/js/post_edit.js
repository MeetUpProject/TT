document.addEventListener("DOMContentLoaded", () => {
    const urlParams = new URLSearchParams(window.location.search);
    const postId = urlParams.get("id");

    if (!postId) {
        alert("게시글 ID가 없습니다.");
        return;
    }

    document.getElementById("postId").value = postId;

    // 게시글 데이터 불러오기
    fetch(`/posts/${postId}`)
        .then(response => response.json())
        .then(post => {
            document.getElementById("title").value = post.title;
            document.getElementById("content").value = post.content;
            document.getElementById("writer").value = post.writer;
        })
        .catch(err => alert("게시글 불러오기 실패: " + err.message));

    // 수정 제출
    document.getElementById("editForm").addEventListener("submit", function (e) {
        e.preventDefault(); // 기본 form 제출 막기

        const title = document.getElementById("title").value;
        const content = document.getElementById("content").value;
        const password = document.getElementById("password").value;

        if (!password) {
            alert("비밀번호를 입력해주세요.");
            return;
        }

        const updateData = {
            id: Number(postId),
            title,
            content,
            password
        };

        fetch(`/posts`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updateData)
        })
            .then(response => {
                if (response.ok) {
                    alert("수정 완료!");
                    window.location.href = `/post_list.html`;
                } else {
                    alert("수정 실패. 비밀번호를 확인해주세요.");
                }
            })
            .catch(err => alert("오류 발생: " + err.message));
    });
});
