document.addEventListener("DOMContentLoaded", function () {
    // ✅ 현재 URL에서 postId 추출 (예: /post/5 → 5)
    const pathParts = window.location.pathname.split("/");
    const postId = pathParts[pathParts.length - 1];

    if (!postId) {
        alert("게시글 ID가 없습니다.");
        return;
    }

    // hidden input에 ID 설정
    document.getElementById("postId").value = postId;

    // 게시글 상세 조회
    fetch(`/posts/${postId}`)
        .then(response => {
            if (!response.ok) throw new Error("게시글 조회 실패");
            return response.json();
        })
        .then(post => {
            document.getElementById("title").innerText = post.title;
            document.getElementById("content").innerText = post.content;
            document.getElementById("writer").innerText = post.writer ?? '익명';
        })
        .catch(error => {
            console.error(error);
            alert("게시글을 불러오지 못했습니다.");
        });
});

function editPost() {
    const postId = document.getElementById("postId").value;
    const password = document.getElementById("passwordInput").value;

    // 비밀번호 확인 API 호출
    fetch(`/posts/${postId}/verify-password`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ password })
    })
        .then(response => {
            if (response.ok) {
                // ✅ 수정 페이지는 /post/edit/{id} 경로 사용
                window.location.href = `/post/edit/${postId}`;
            } else {
                alert("비밀번호가 틀렸습니다.");
            }
        });
}

function deletePost() {
    const postId = document.getElementById("postId").value;
    const password = document.getElementById("passwordInput").value;

    // 비밀번호 확인
    fetch(`/posts/${postId}/verify-password`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ password })
    })
        .then(response => {
            if (response.ok) {
                // 실제 삭제
                return fetch(`/posts/${postId}`, { method: "DELETE" });
            } else {
                throw new Error("비밀번호 오류");
            }
        })
        .then(response => {
            if (response.ok) {
                alert("삭제 완료");
                // ✅ 목록으로 이동
                window.location.href = "/post/list";
            } else {
                alert("삭제 실패");
            }
        })
        .catch(err => {
            alert(err.message);
        });
}
