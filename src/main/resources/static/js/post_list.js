document.addEventListener('DOMContentLoaded', () => {
    loadPosts(); // 페이지 로딩 시 전체 목록 표시
});

function loadPosts(keyword = "") {
    let url = '/posts/list';
    if (keyword) {
        url += `?keyword=${encodeURIComponent(keyword)}`;
    }

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const postList = document.getElementById("postList");
            postList.innerHTML = '';

            const list = data.dtoList;

            if (!list || list.length === 0) {
                postList.innerHTML = "<p>🔍 검색 결과가 없습니다.</p>";
                return;
            }

            list.forEach(post => {
                const postElement = document.createElement('div');
                postElement.innerHTML = `
                    <p>
                        <strong>${post.title}</strong><br>
                        <span>${post.writer ?? '익명'}</span>
                    </p>
                    <button onclick="location.href='/post/${post.id}'">상세</button>
                    <hr>
                `;
                postList.appendChild(postElement);
            });
        })
        .catch(error => {
            console.error('게시글 목록 로딩 실패:', error);
        });
}

function searchPosts() {
    const keyword = document.getElementById("searchInput").value;
    console.log("검색어:", keyword);
    loadPosts(keyword); // 검색어 기반 목록 다시 로딩
}
