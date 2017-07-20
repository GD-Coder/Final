'use strict'

const ExtractTextPlugin = require('extract-text-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')

const devtool = 'source-map'

const entry = {
  vendor: './src/vendor.js',
  main: ['babel-polyfill', './src/main.js']
}

const output = {
  filename: '[name].js',
  path: '../src/main/resources/static/'
}

const extensions = [
  '',
  '.js',
  '.css',
  '.html'
]

const loaders = [{
  test: /.js$/,
  exclude: /node_modules/,
  loaders: ['ng-annotate', 'babel']
}, {
  test: /.css$/,
  loader: ExtractTextPlugin.extract('style', 'css')
}, {
  test: /.html$/,
  include: /src/,
  exclude: /node_modules/,
  loaders: ['ngtemplate', 'html']
}, {
  test: /.html$/,
  include: /static/,
  exclude: /node_modules/,
  loader: 'html'
}, {
  test: /.(ico|png|eot|svg|ttf|woff|woff2)$/,
  loader: 'url?limit=10000'
}]

const plugins = [
  new ExtractTextPlugin('[name].css'),
  new HtmlWebpackPlugin({
    hash: true,
    template: './static/index.html',
    inject: 'head'
  })
]

module.exports = {
  devtool,
  entry,
  output,
  resolve: {
    extensions
  },
  module: {
    loaders
  },
  plugins
}
