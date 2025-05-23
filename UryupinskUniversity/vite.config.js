import { resolve } from "path";
import { defineConfig } from "vite";

export default defineConfig({
    root: "src", // Указываем, что корень проекта - папка src
    build: {
        rollupOptions: {
            input: {
                main: resolve(__dirname, "src/index.html"),
                applicants: resolve(__dirname, "src/applicants.html"),
                contacts: resolve(__dirname, "src/contacts.html"),
                faculties: resolve(__dirname, "src/faculties.html"),
                research: resolve(__dirname, "src/research.html"),
            },
            outDir: "../dist", // Папка для сборки
        },
    },
    server: {
        port: 5173,
        open: "/index.html", // Автоматически открывать index.html
    },
});
