var upUrl = ctx + "system/file/upload";
var delUrl = ctx + "system/file/delUpload";
var realmName = $("#realmName").val();
//记录初始文件信息
var fileList = new Array();
//记录改变文件信息
var fileTypeList = [];
//记录改变多文件ID
var ids = [];

function editInitFileInput() {
    $(document).ready(function () {
        $(".fileinput").fileinput({
            uploadUrl: upUrl,//初始化url参数能否重新赋值
            deleteUrl: delUrl,
            allowedFileExtensions: ['jpg', 'zip', 'png', 'pdf', 'jpeg'],//接收的文件后缀
            showRemove: false,
            showCaption: false,//是否显示标题
            uploadAsync: true,
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            maxFileCount: 5, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount: true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            layoutTemplates: {
                actionUpload: '',//去除上传预览缩略图中的上传图片；
            },
        });
    });
};


$(".fileinput").on("fileuploaded", function (event, data, previewId, index) {
    var resq = data.response;
    var data = resq.data;
    if (0 == resq.code){
        if ("fileType_1" == data.fileType){
            var aryFile = {caption: data.fileName,url: data.fileUrl, key: data.key, type: data.fileType, previewId: previewId};
            fileTypeList.push(aryFile);
        }
        if ("fileType_2" == data.fileType){
            var aryFile = {caption: data.fileName,url: data.fileUrl, key: data.key, type: data.fileType, previewId: previewId};
            fileTypeList.push(aryFile);
        }
        if ("fileType_3" == data.fileType){
            var aryFile = {caption: data.fileName,url: data.fileUrl, key: data.key, type: data.fileType, previewId: previewId};
            fileTypeList.push(aryFile);
        }
        if ("fileType_4" == data.fileType){
            var aryFile = {caption: data.fileName,url: data.fileUrl, key: data.key, type: data.fileType, previewId: previewId};
            fileTypeList.push(aryFile);
        }
    }else {
        $.modal.alertError(data.response.msg);
    }
}).on('fileerror', function(event, data, msg) {
    $.modal.msgWarning(msg);
}).on('filesuccessremove', function(event, id) {
    if (fileTypeList.length > 0) {
        var arrList = fileTypeList;
        fileTypeList = [];
        for (var j = 0; j < arrList.length; j++){
            if (id != arrList[j].previewId){
                var aryFile = {caption: arrList[j].caption,url: arrList[j].url, key: arrList[j].key, type: arrList[j].type, previewId: arrList[j].previewId};
                fileTypeList.push(aryFile);
            }else {
                ids.push(arrList[j].key);
            }
        };
    }
}).on("filepredelete", function (e, key, jqXHR, data) { // 预览中删除按钮，删除上传的文件触发的事件
    if (fileList.length > 0) {
        var arrList = fileList;
        fileList = [];
        for (var j = 0; j < arrList.length; j++){
            if (key != arrList[j].key ){
                var aryFile = {caption: arrList[j].caption,url: arrList[j].url, key: arrList[j].key, type: arrList[j].type};
                fileList.push(aryFile);
            }
        };
    }else {
        $.modal.alertError("fileList有误");
    }
}).on("fileloaded", function (e, file, previewId) {// 加载预览后触发的事件
    if (fileList.length > 0){
        var arrFileList = fileList;
        fileList = [];
        for (var j = 0; j < arrFileList.length; j++){
            if (e.currentTarget.id != arrFileList[j].type){
                var aryFile = {caption: arrFileList[j].caption,url: arrFileList[j].url, key: arrFileList[j].key, type: arrFileList[j].type};
                fileList.push(aryFile);
            }else {
                ids.push(arrFileList[j].key);
            }
        }
    }
    $.modal.msgSuccess("记得要点击上传按钮哦！");
}).on("filecleared",function(event, data, msg){//浏览框右上角X 清空文件后响应事件
    if (fileList.length > 0){
        var arrFileList = fileList;
        fileList = [];
        for (var j = 0; j < arrFileList.length; j++){
            if (event.currentTarget.id != arrFileList[j].type ){
                var aryFile = {caption: arrFileList[j].caption,url: arrFileList[j].url, key: arrFileList[j].key, type: arrFileList[j].type};
                fileList.push(aryFile);
            }else {
                ids.push(arrFileList[j].key);
            }
        }
    }
    if (fileTypeList.length > 0){
        var arrFileTypeList = fileTypeList;
        fileTypeList = [];
        for (var j = 0; j < arrFileTypeList.length; j++){
            if (event.currentTarget.id != arrFileTypeList[j].type ){
                var aryFile = {caption: arrFileTypeList[j].caption,url: arrFileTypeList[j].url, key: arrFileTypeList[j].key, type: arrFileTypeList[j].type};
                fileTypeList.push(aryFile);
            }else {
                ids.push(arrFileTypeList[j].key);
            }
        }
    }
});
function newFileList(){
    var array = mergeArray(fileList,fileTypeList);
    fileList = [];
    fileList = array;
    if (validatefile(fileList)){
        //将文件转化 orderFileIds
        arryToIds();
    }
}
function validatefile(data) {
    var state = true;
    //验证是否都已上传文件。
    if (data.length == 0){
        state = false;
        $.modal.alertWarning("转账凭证,客户银行卡,客户身份证,合同附件,需上传相应的文件");
    }else if (data.length > 0){
        var intoType = [];
        for (var i = 0; i < data.length; i++){
            intoType.push(data[i].type, data[i].typeName);
        }
        var typeList = [];
        var type1="fileType_1";var type2="fileType_2"; var type3="fileType_3"; var type4="fileType_4";
        typeList.push(type1); typeList.push(type2); typeList.push(type3); typeList.push(type4);
        var restType = getArrDifference(intoType, typeList);
        var msg = "";
        for (var j = 0; j < restType.length; j++){
            if(restType[j] == "fileType_1"){
                msg+=("转账凭证,");state = false;
            }
            if ( restType[j] == "fileType_2"){
                msg+=("客户银行卡,");state = false;
            }
            if(restType[j] == "fileType_3"){
                msg+=("客户身份证,");state = false;
            }
            if(restType[j] == "fileType_4"){
                msg+=("合同附件,");state = false;
            }
        }
        if (msg != ""){
            $.modal.alertWarning(msg + "需上传相应的文件");
        }
    };
    return state;
}

function mergeArray(arr1,arr2){
    var _arr = new Array();
    for(var i=0;i<arr1.length;i++){
        _arr.push(arr1[i]);
    }
    for(var i=0;i<arr2.length;i++){
        var flag = true;
        for(var j=0;j<arr1.length;j++){
            if(arr2[i]==arr1[j]){
                flag=false;
                break;
            }
        }
        if(flag){
            _arr.push(arr2[i]);
        }
    }
    return _arr;
}

function getArrDifference(arr1, arr2) {
    return arr1.concat(arr2).filter(function(v, i, arr) {
        return arr.indexOf(v) === arr.lastIndexOf(v);
    });
};
function arryToIds() {
    if (fileList.length > 0){
        $("#orderFileIds").val(listToids(fileList));
        $("#delOrderFileIds").val(unique(ids));
    }
}

//数组去重
function unique(arr){
    var newArr = "";
    for(var i = 0; i < arr.length; i++){
        for(var j = i+1; j < arr.length; j++){
            if(arr[i] == arr[j]){
                ++i;
            }
        }
        newArr += (arr[i]) + ",";
    }
    return newArr;
}
//取数组中的ID
function listToids(data) {
    var ids = "";
    for (var j = 0; j < data.length; j++){
        ids += data[j].key + ",";
    };
    return ids;
};
