import react from "@vitejs/plugin-react";
import { resolve } from "path";
import { defineConfig } from "vite";

export default defineConfig({
    plugins: [react()],
    root: "src",
    build: {
        rollupOptions: {
            input: {
                main: resolve(__dirname, "src/index.html"),
            },
            outDir: "../dist",
        },
    },
});
