const path = require('path')
const webpack = require('webpack')

module.exports = {
  entry: {
    admin: './resources/admin/index.js',
    store: './resources/store/index.js'
  },
  externals: [{"jquery": "window.jQuery"}],
  output: {
    path: path.resolve(__dirname, './public/static/scripts'),
    publicPath: '/scripts/',
    filename: '[name]-bundle.js'
  },
  module: {
    rules: [
      {
        test: /\.vue$/,
        loader: 'vue-loader',
        options: {
          loaders: {
            css : "css-loader",
            less : "css-loader!less-loader",
            scss: 'css-loader!sass-loader', // <style lang="scss">
            sass: 'css-loader!sass-loader?indentedSyntax' // <style lang="sass">
          }
        }
      },
      {
        test: /\.js$/,
        loader: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.(png|jpe?g|gif|svg)$/,
        loader: 'file-loader',
        options: {
          name: '[name].[ext]?[hash]'
        }
      },
      {
        test: /\.css$/,
        loader: "vue-style-loader!css-loader",
      },
      {
        test: /\.scss$/,
        loader: "vue-style-loader!css-loader!sass-loader"
      },
      {
        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
        loader: 'url-loader'
      }
    ]
  },
  plugins: [
    new webpack.ProvidePlugin({
      $: "jquery",
      jQuery: "jquery"
    })
  ],
  resolve: {
    alias: {
      'vue$': 'vue/dist/vue.esm.js',
    }
  }
  ,
  devServer: {
    contentBase: "./public/static",
    publicPath: "/scripts/",
    noInfo: false, //  --no-info option
    hot: true,
    inline: true,
  }
  ,
  performance: {
    hints: false
  }
  ,
  devtool: '#eval-source-map'
}