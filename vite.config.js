import { defineConfig } from "vite";

export default defineConfig({
    build: {
        rollupOptions: {
            input: {
                main: "index.html",
                applicants: "applicants.html",
                contacts: "contacts.html",
                faculties: "faculties.html",
                research: "research.html",
            },
        },
    },
});
