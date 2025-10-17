import NewsController from "../components/js-mvc-4/controller.js";
import NewsModel from "../components/js-mvc-4/model.js";
import NewsView from "../components/js-mvc-4/view.js";

document.addEventListener("DOMContentLoaded", () => {
    const model = new NewsModel();
    const view = new NewsView();
    new NewsController(model, view);
});
