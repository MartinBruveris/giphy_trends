module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  configureWebpack: {
    entry: {
      app: './src/main.js' // or whatever your entry is
    }
}
}