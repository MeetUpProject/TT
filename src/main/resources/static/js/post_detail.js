// post_detail.js

document.addEventListener("DOMContentLoaded", function () {
    // URL에서 postId 추출 (예: /post_detail.html?id=3)
    const urlParams = new URLSearchParams(window.location.search);
    const postId = urlParams.get("id");

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
            document.getElementById("writer").innerText = post.writer;
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
                window.location.href = `/post_edit.html?id=${postId}`; // 수정 페이지로 이동
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
                window.location.href = "/post_list.html";
            } else {
                alert("삭제 실패");
            }
        })
        .catch(err => {
            alert(err.message);
        });
}

// 수정 및 삭제는 추가적으로 postId와 passwordInput 값 이용하여 fetch 요청 보내면 됩니다.
