module.exports = {
  root: true,
  env: {
    node: true
  },
  extends: ["plugin:vue/essential", "@vue/prettier", "@vue/typescript"],
  rules: {
    "no-console": process.env.NODE_ENV === "production" ? "error" : "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off",
      quotes: [
          2,
          "single",
          "avoid-escape"
      ],
      semi: [
          "error",
          "never"
      ]
  },
  parserOptions: {
    parser: "typescript-eslint-parser"
  }
};
