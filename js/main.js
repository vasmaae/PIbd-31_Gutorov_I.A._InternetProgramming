document.getElementById("newsForm").addEventListener("submit", (e) => {
    e.preventDefault();
    const title = document.getElementById("newsTitle").value.trim();
    const content = document.getElementById("newsContent").value.trim();
    const imageInput = document.getElementById("newsImage");
    const newsContainer = document.getElementById("newsContainer");

    if (title && content) {
        const newArticle = document.createElement("div");
        newArticle.className = "card mb-3";
        let imageHtml = "";
        if (imageInput.files && imageInput.files[0]) {
            const imageUrl = URL.createObjectURL(imageInput.files[0]);
            imageHtml = `<img src="${imageUrl}" alt="${title}" class="card-img-top" >`;
        }
        newArticle.innerHTML = `
            <div class="card-body">
                ${imageHtml}
                <h3 class="card-title">${title}</h3>
                <p class="card-text">${content}</p>
            </div>
        `;
        newsContainer.prepend(newArticle);
        document.getElementById("newsTitle").value = "";
        document.getElementById("newsContent").value = "";
        imageInput.value = "";
        document.getElementById("imagePreview").style.display = "none";
    } else {
        alert("Пожалуйста, заполните все поля.");
    }
});

document.getElementById("newsImage").addEventListener("change", (e) => {
    const imagePreview = document.getElementById("imagePreview");
    const previewImg = document.getElementById("previewImg");
    if (e.target.files && e.target.files[0]) {
        previewImg.src = URL.createObjectURL(e.target.files[0]);
        imagePreview.style.display = "block";
    } else {
        imagePreview.style.display = "none";
    }
});
