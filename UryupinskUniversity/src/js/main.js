import NewsController from "../components/news/controller.js";
import NewsModel from "../components/news/model.js";
import NewsView from "../components/news/view.js";

document.addEventListener("DOMContentLoaded", () => {
    const model = new NewsModel();
    const view = new NewsView();
    new NewsController(model, view);
});
