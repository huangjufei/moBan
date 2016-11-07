/**
 * 改变freemarker文件中所有静态资源的版本号。
 * 
 */

'use strict';

var fs = require('fs'), async = require('async'), config = require('../config.js'), storage = require('../storage.js'), utils = require('../utils.js');

var version = storage.get('version');
//var versionExpression = /("\/static)(]\/[^"\?*)(?:\?v=(\${10}))"/ig;
var versionExpression ='String version=""';
var changeVersion = function(path) {
  var originalContent = fs.readFileSync(path).toString();
	console.log(originalContent);
  var hasChanged = false;
  //$0表示匹配到的字符，$1 表示匹配到的字符串RegExp.input（括号里面）,$2,表示匹配到的字符的最小索引位置(RegExp.index)
  var changtargetString=function() {
	   /* utils.log('需要修改版本号的的URL' + $0);
	    var target = $1 + '/' + version + $2 + '"';
	    hasChanged = true;
	    utils.log('替换成的目标URL' + target);
	    return target;*/
	      hasChanged = true;
	      return 'String version="'+ version+'"';
	  };
	  console.log(changtargetString());
  var changedContent = originalContent.replace(versionExpression,changtargetString());
  console.log("change"+changedContent);
  utils.log('修改目标文件' + path);
  fs.writeFileSync(path, changedContent);
  
  /*if (hasChanged) {
    utils.log('修改目标文件' + path);
    fs.writeFileSync(path, changedContent);
  }*/
};

var walkFiles = function(path, callback) {
//  var filesList = fs.readdirSync(path);
//  filesList.forEach(function(currentFile) {
//    var tPath = path + '/' + currentFile;
//    if (fs.statSync(path).isDirectory()) {
//      walkFiles(path);
//    } else {
      changeVersion(path);
//    }
//  });
};

exports.run = function(callback) {
  try {
    utils.log('开始修改目录版本号'+ config.freemarkerFolder);
//    config.freemarkerFolder.forEach(function(folder) {
//      utils.log('开始遍历目录' + folder);
//      walkFiles(folder, callback);
//    });
   
        walkFiles(config.freemarkerFolder, callback);
    
    utils.log('目录版本号修改完成！\n');
    callback();
  } catch (e) {
    callback(e);
  }
};