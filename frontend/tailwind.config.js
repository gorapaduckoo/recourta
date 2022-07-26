module.exports = {
    content: ["./public/**/*.html", "./src/**/*.{html,js,vue,ts,tsx,jsx}", "./node_modules/flowbite/**/*.js"],
    // theme: {
    //   extend: {},
    // },
    plugins: [
        require('flowbite/plugin')
    ],
}