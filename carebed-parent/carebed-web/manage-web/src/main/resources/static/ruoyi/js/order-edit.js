var minAmount = 0;
var maxAmount = 9999;
var prefix = ctx + "system/order";
var icon = "<i class='fa fa-times-circle'></i> ";

$("#form-order").validate({
    onkeyup: false,
    focusInvalid: true,
    rules:{
        customerMobile:{
            isPhone:true
        },
        paymentNum : {
            required:true,
            digits:true,
            min: 1,
            max: 100
        }/*,
        repayBankNum : {
            creditcard:true,
        }*/
    },
    messages:{
        paymentNum:{
            digits: icon + "请输入正整数"
        }/*,
        repayBankNum : {
            creditcard: icon + "请输入有效的银行卡号码",
        }*/
    },
    invalidHandler: function(form, validator) {
        var errors = validator.numberOfInvalids();
        if (errors) {
            validator.errorList[0].element.focus();
        }
    }
});

/**
 * 加载远程数据
 * @param uri
 * @param reqData
 * @param callback
 */
function loadRemoteData(uri,reqData,callback) {
    $.ajax({
        url: ctx + uri,
        type: "POST",
        async: false,
        dataType: 'json',
        data : reqData,
        success: callback
    });
}

/*编辑-选择部门树*/
function selectDeptTree() {
    console.log("编辑-选择部门树");
    var treeId = $("#treeId").val();
    var deptId = $.common.isEmpty(treeId) ? "100" : $("#treeId").val();
    var url = ctx + "system/dept/getDeptTreeToOrder/" + deptId + "/edit";
    var options = {
        title: '选择部门',
        width: "380",
        url: url,
        callBack: doSubmit
    };
    $.modal.openOptions(options);
}

function doSubmit(index, layero){
    var tree = layero.find("iframe")[0].contentWindow.$._tree;
    var body = layer.getChildFrame('body', index);
    $("#treeId").val(body.find('#treeId').val());
    $("#treeName").val(body.find('#treeName').val());

    layer.close(index);
    var deptId = $("#treeId").val();
    if($.common.isNotEmpty(deptId)){
        var reqData = {"deptId" : deptId};
        loadRemoteData("system/user/infoByDeptCode",reqData,dealUserData);
        loadRemoteData("system/info/loadEqNumsByDeptId",reqData,dealEqNum);
    }
    $("#form-order").validate().element($("#treeName"));
}

/**
 * 根据选中的部门编号初始化此部门下员工信息
 * @param deptId
 */
function dealUserData(result){
    var $signingUserId = $("#signingUserId");
    var obj = eval(result);
    $signingUserId.empty();
    $signingUserId.append(new Option('', ''));
    for (var i = 0; i < obj.length; i++) {
        var v = obj[i];
        var deptCode = v.deptCode;
        if($.common.isEmpty(deptCode)){deptCode = '空'}
        var userCardCode = v.userCardCode;
        if($.common.isEmpty(userCardCode)){userCardCode = '空'}
        var city = v.city;
        if($.common.isEmpty(city)){city = '空'}
        var userCode = v.userCode;
        if($.common.isEmpty(userCode)){userCode = '空'}
        $signingUserId.append('<option value=' + v.userId + ' deptCode='+ deptCode + ' userCardCode=' + userCardCode + ' city= ' + city + ' userCode=' + userCode +'>' + v.userName + '</option>');
    }
    initUserInfo();
}

//初始化员工数据
function  initUserInfo() {
    var $signingUserId = $("#signingUserId");
    var userId = $signingUserId.val();
    var deptCode= '';
    var userCardCode='';
    var city='';
    var userCode='';
    if($.common.isNotEmpty(userId)){
        deptCode= $signingUserId.find("option:selected").attr("deptCode");
        userCardCode= $signingUserId.find("option:selected").attr("userCardCode");
        city= $signingUserId.find("option:selected").attr("city");
        userCode= $signingUserId.find("option:selected").attr("userCode");
    }
    if($.common.equals('空',deptCode)){
        deptCode = '';
    }
    if($.common.equals('空',userCardCode)){
        userCardCode = '';
    }
    if($.common.equals('空',city)){
        city = '';
    }
    if($.common.equals('空',userCode)){
        userCode = '';
    }
    $("#signUserId").val(userId);
    $("#signUserCode").text(userCode);
    $("#signUserCardCode").text(userCardCode);
    $("#deptCode").text(deptCode);
    $("#city").text(city);
}
function formatSignSelectText(repo){
    var text = repo.text;
    if($.common.isNotEmpty(text)){
        return $.common.split(text,"-")[0];
    }
    return text;
}
//处理设备编号
function dealEqNum(result){
    $("#equipmentIds").empty();
    if($.common.isNotEmpty(result) && result.length > 0){
        for (var i = 0; i < result.length; i++) {
            $("#equipmentIds").append(new Option(result[i].equipmentNumber,result[i].equipmentId));
        }
    }
}

/**
 * 动态的给金额添加最少金额起投验证
 * @param leastAmount
 */
function valAmount(leastAmount){
    if($.common.isEmpty(leastAmount) || leastAmount <= minAmount){
        leastAmount = minAmount;
    }
    $("#subscriptionAmount").rules("remove");
    $("#subscriptionAmount").rules('add', {
        digits:true,
        gtAmount: leastAmount,
        itAmount: maxAmount,
        messages:{
            digits: icon + "请输入正整数"
        }
    });
    var money = $("#subscriptionAmount").val();
    if($.common.isNotEmpty(money)){
        $("#form-order").validate().element($("#subscriptionAmount"));
    }
}
/**
 * 加载产品期数的下拉框
 * @param productPeriods
 */
function loadProductPeriods(productPeriods){
    var $productPeriod = $("#productPeriod");
    $productPeriod.empty();
    $productPeriod.append(new Option('', ''));
    if($.common.isNotEmpty(productPeriods)){
        var prodPers = $.common.split(productPeriods,",");
        for (var i = 0; i < prodPers.length; i++) {
            $productPeriod.append(new Option(prodPers[i],prodPers[i]));
        }
    }
}
/**
 * 加载付息方式
 */
function loadInterestPaymentWay(){
    var prodId = $("#productId").val();
    var prodPer = $("#productPeriod").val();
    var reqData = {'productId':prodId,'productPeriod':prodPer}
    loadRemoteData('system/detail/findInterestPaymentWays',reqData,dealInterestPaymentWay);
}

/**
 * 处理付息方式
 */
function dealInterestPaymentWay(result){
    for (var i = 0; i < result.length; i++) {
        $("#interestPaymentWay").append(new Option(result[i].interestPaymentWayName,result[i].interestPaymentWay));
    }
}

/**
 *  加载产品利率
 */
function loadProductRate(){
    var prodId = $("#productId").val();
    var prodPer = $("#productPeriod").val();
    var interestPaymentWay = $("#interestPaymentWay").val();
    var reqData = {'productId':prodId,'productPeriod':prodPer,'interestPaymentWay':interestPaymentWay};
    if($.common.isNotEmpty(interestPaymentWay)){
        loadRemoteData('system/detail/findProductDetailByParam',reqData,dealProdRate);
    }
}
/**
 * 处理产品利率
 */
function dealProdRate(result){
    if($.common.isNotEmpty(result)){
        $("#productRateV").text(result[0].productRate);
        $("#productRate").val(result[0].productRate);
    }
}

/**
 * 动态添加验证处理客户身份证号
 */
function validateCNum(){
    var ctype = $("#certificateType").val();
    $("#certificateNum").rules("remove");
    if($.common.isNotEmpty(ctype) && ctype == 1){
        //动态添加身份证号的表单验证
        $("#certificateNum").rules("add",{isIdentity:true});
    }
    if($.common.isNotEmpty(ctype)){
        $("#form-order").validate().element($("#certificateNum"))
    }
}

/**
 * dealCusInfoByCNum 远程加载客户信息通过证件号
 */
function dealCusInfoByCNum(result) {
    var obj = result.data;
    if(obj.cusInfo.length>0){
        var o = obj.cusInfo[0];
        $("#customerId").val(o.customerId);
        $("#customerName").val(o.customerName);
        $("#repayBankNum").val(o.bankCardNum);
        $("#customerMobile").val(o.customerMobile);
        $("#bankName").val(o.bankName);
        $("#customerAddr").val(o.customerAddr);
        $("#bankOpeningBranch").val(o.openBankName);
        $("#form-order-add").validate().element($("#customerName"));
    }/*else{
        $("#customerId").val('');
        $("#customerName").val('');
        $("#repayBankNum").val('');
        $("#customerMobile").val('');
        $("#bankName").val('');
        $("#customerAddr").val('');
        $("#bankOpeningBranch").val('');
    }*/
    if($.common.isNotEmpty(obj.tipsMsg)){
        $.modal.alertWarning(obj.tipsMsg);
    }
}

/**
 * 处理回款日期的时间数据
 */
function dealPaymentDate(lendDate){
    var productPeriod = $("#productPeriod").val();
    console.log("lendDate"+ lendDate);
    if($.common.isNotEmpty(productPeriod)){
        /*var addY = parseInt(productPeriod/12);
        var addM = productPeriod%12;
        var gaga = new Date(lendDate),
            _y = gaga.getFullYear(),
            _m = gaga.getMonth(),
            _d = gaga.getDate();
        var new_gaga = new Date(_y+addY,_m + addM,_d-1),
            new_y = new_gaga.getFullYear(),
            new_m = new_gaga.getMonth()+1,
            new_d = new_gaga.getDate();
        if(new_m < 10){
            new_m = '0' + new_m;
        }
        if(new_d < 10){
            new_d = '0' + new_d;
        }
        console.log(new_y+'-'+new_m+'-'+new_d)
        $("#accountPaymentDate").val(new_y+'-'+new_m+'-'+new_d);*/
        var gaga = new Date(lendDate),
            _y = gaga.getFullYear(),
            _m = gaga.getMonth() + 1,
            _d = gaga.getDate();
        console.log(_y+'-'+_m+'-'+_d);
        var reqData = {'productPeriod':productPeriod,'lendMoneyDate':_y+'-'+_m+'-'+_d};
        loadRemoteData('system/plan/getAccountPaymentDate',reqData,dealAccountPaymentDate);
    }else {
        $("#accountPaymentDate").val('');
    }
}

/**
 *  处理合同的到期日期
 **/
function dealAccountPaymentDate(result) {
    $("#accountPaymentDate").val(result.accountPaymentDate);
}

/**
 * 处理续投合同和是否在库中已经存在
 * @param result
 */
function dealOrgContractNoIsRepeat(result){
    if(!$.common.equals(result.code,'0')){
        $.modal.alertError(result.msg);
    }
}


$(function () {
    $('#lendMoneyDateDiv').datetimepicker({
        minView: "month",
        format: 'yyyy-mm-dd',
        endDate: new Date(),
        autoclose: true
    });
    $(".minimumResultsForSearch").select2({minimumResultsForSearch: -1});//让下拉列表不显示搜索栏
    $("#signingUserId").select2({placeholder: '请选择',language: "zh-CN",allowClear: true,closeOnSelect: true,templateSelection:formatSignSelectText});
    $("#signingUserId").on("change",function(data){ initUserInfo();});
    $("#certificateType").select2({minimumResultsForSearch: -1,placeholder: '请选择',language: "zh-CN",allowClear: true,closeOnSelect: true});
    $("#productPeriod").select2({minimumResultsForSearch: -1,placeholder: '请选择',language: "zh-CN",allowClear: true,closeOnSelect: true});
    $("#paymentMethod").select2({minimumResultsForSearch: -1,placeholder: '请选择',language: "zh-CN",allowClear: true,closeOnSelect: true});
    $("#productId").select2({minimumResultsForSearch: -1,placeholder: '请选择',language: "zh-CN",allowClear: true,closeOnSelect: true});
    $("#interestPaymentWay").select2({minimumResultsForSearch: -1,placeholder: '请选择',language: "zh-CN",allowClear: true,closeOnSelect: true});
    $("#continuedInvestment").select2({minimumResultsForSearch: -1,placeholder: '请选择',language: "zh-CN",allowClear: true,closeOnSelect: true});
    $("#equipmentIds").select2({placeholder: '请选择',language: "zh-CN",allowClear: true,closeOnSelect: true});
    $("#productAccountId").select2({minimumResultsForSearch: -1,placeholder: '请选择',language: "zh-CN",allowClear: true,closeOnSelect: true});

    $("#contractNoSuffix").on('focus',function(){
        var optProd = $("#productId").val();
        if($.common.isNotEmpty(optProd)){
            $("#contractNoSuffix").removeAttr('readonly');
        }else{
            $("#contractNoSuffix").attr('readonly',true);
            $.modal.msgWarning("请先选择产品");
        }
    });

    $("#subscriptionAmount").on('focus',function(){
        var optProd = $("#productId").val();
        if($.common.isNotEmpty(optProd)){
            $("#subscriptionAmount").removeAttr('readonly');
        }else{
            $("#subscriptionAmount").attr('readonly',true);
            $.modal.msgWarning("请先选择产品");
        }
    });

    //签约员工
    var reqData = {"deptId" : $("#treeId").val()};
    loadRemoteData("system/user/infoByDeptCode",reqData,dealUserData);
    $("#signingUserId").val($("#hxsigningUserId").val()).trigger("change");//设置员工选中
    //设备编号
    loadRemoteData("system/info/loadEqNumsByDeptId",reqData,dealEqNum);
    //设置设备编号选中
    var eqNumsStr = $("#hxEquipmentIds").val();
    if($.common.isNotEmpty(eqNumsStr)){
        var eqNums = $.common.split(eqNumsStr,",");
        $("#equipmentIds").val(eqNums).select2();
    }
    //设置选中的身份证号类型
    var hxcertificateType = $("#hxcertificateType").val();
    if($.common.isNotEmpty(hxcertificateType)){
        $("#certificateType").val(hxcertificateType).trigger("change");
    }
    $("#certificateType").on("change",function(data){validateCNum()});
    $("#certificateNum").change(function() {
        validateCNum();
        var cNum =  $.common.trim($("#certificateNum").val());
        if($.common.isNotEmpty(cNum)){
            var reqData = {'certificateNum': cNum};
            loadRemoteData("system/customer/cusInfoByCNum",reqData,dealCusInfoByCNum);
        }
    });

    valAmount(minAmount);
    loadProductPeriods('');
    $("#productId").change(function() {
        $("#contractNoSuffix").removeAttr('readonly');
        $("#subscriptionAmount").removeAttr('readonly');
        var $productId = $("#productId");
        var prodId =  $.common.trim($productId.val());
        if($.common.isNotEmpty(prodId)){
            $("#contractNoPrefix").text($productId.find("option:selected").attr("prodContPre"));
            $("#prodContPre").val($productId.find("option:selected").attr("prodContPre"));
            $("#productType").val($productId.find("option:selected").attr("productType"));
            $("#prodContTypeV").text($productId.find("option:selected").attr("productTypeName"));
            valAmount($productId.find("option:selected").attr("leastAmount"));
            loadProductPeriods($productId.find("option:selected").attr("productPeriods"));
            $("#productPeriod").val('').trigger("change");
        }else{
            $("#prodContPre").text('');
            $("#contractNoPrefixV").val('');
            $("#productType").val('');
            $("#prodContTypeV").text('');
            valAmount(minAmount);
            $("#productPeriod").val('').trigger("change");
        }
    });
    //设置产品选中
    var hxProductId = $("#hxProductId").val();
    if($.common.isNotEmpty(hxProductId)){
        $("#productId").val(hxProductId).trigger("change");
    }

    $("#productPeriod").change(function() {
        var prodPer = $("#productPeriod").val();
        var $interestPaymentWay = $("#interestPaymentWay");
        $interestPaymentWay.empty();
        $interestPaymentWay.append(new Option('', ''));
        $interestPaymentWay.val('').trigger("change");
        if($.common.isNotEmpty(prodPer)){
            loadInterestPaymentWay();
        }
        var lendDate = $("#lendMoneyDate").val();
        if($.common.isNotEmpty(lendDate)){
            dealPaymentDate(lendDate);
        }
    });
    //设置产品的期数选中
    var hxProductPeriod = $("#hxProductPeriod").val();
    if($.common.isNotEmpty(hxProductPeriod)){
        $("#productPeriod").val(hxProductPeriod).trigger("change");
    }

    $("#interestPaymentWay").change(function() {
        var interestPaymentWay = $("#interestPaymentWay").val();
        if($.common.isNotEmpty(interestPaymentWay)){
            loadProductRate();
        }else{
            $("#productRateV").text("");
            $("#productRate").val("");
        }
    });
    //设置付息方式选中
    var hxInterestPaymentWay = $("#hxInterestPaymentWay").val();
    if($.common.isNotEmpty(hxInterestPaymentWay)){
        $("#interestPaymentWay").val(hxInterestPaymentWay).trigger("change");
    }

    //回显合同号
    var contractNo = $("#contractNo").val();
    var prodContPre = $("#prodContPre").val();
    if($.common.isNotEmpty(contractNo) && $.common.isNotEmpty(prodContPre) && $.common.startWith(contractNo,prodContPre)){
        $("#contractNoSuffix").val(contractNo.substring(prodContPre.length));
    }

    $("#paymentMethod").change(function() {
        $("#equipmentIds").rules("remove");
        var paymentMethod = $("#paymentMethod").find("option:selected").text();
        if($.common.equalsIgnoreCase(paymentMethod,"续投")){
            $("#continuedInvestment").val("1").trigger("change");
        }else if($.common.equalsIgnoreCase(paymentMethod,"刷卡")){
            $("#equipmentIds").rules('add', {
                required:true,
                messages:{
                    required:"入资方式刷卡设备编号必填"
                }
            });
            $("#continuedInvestment").val($("#continuedInvestment").val()).trigger("change");
        }else{
            $("#continuedInvestment").val($("#continuedInvestment").val()).trigger("change");
        }
        $("#form-order").validate().element($("#equipmentIds"));
    });

    var hxPaymentMethod = $("#hxPaymentMethod").val();
    if($.common.isNotEmpty(hxPaymentMethod)){
        $("#paymentMethod").val(hxPaymentMethod).trigger("change");
    }

    $("#continuedInvestment").change(function() {
        var paymentMethod = $("#paymentMethod").find("option:selected").text();
        var cti = $("#continuedInvestment").val();
        $("#ciOrgContractNo").rules("remove");
        if($.common.isNotEmpty(cti) && ($.common.equals(cti,1) || $.common.equalsIgnoreCase(paymentMethod,"续投"))){
            $("#ciOrgContractNo").rules('add', {
                required:true,
                messages:{
                    required:"请输入续投原合同编号"
                }
            });
        }
        if($.common.equalsIgnoreCase(paymentMethod,"续投")){
            $('#continuedInvestment').prop('disabled', true);
        }else{
            $('#continuedInvestment').prop('disabled', false);
        }
        if($.common.equals(cti,0)){
            $("#ciOrgContractNo").val("");
            $("#ciOrgContractNo").attr('readonly',true);
        }else{
            $("#ciOrgContractNo").removeAttr('readonly');
        }
        $("#form-order").validate().element($("#ciOrgContractNo"));
    });
    var hxContinuedInvestment = $("#hxContinuedInvestment").val();
    if($.common.isNotEmpty(hxContinuedInvestment)){
        $("#continuedInvestment").val(hxContinuedInvestment).trigger("change");
    }

    $('#lendMoneyDateDiv').datetimepicker().on('changeDate', function(ev){
        dealPaymentDate(ev.date);
        $("#form-order").validate().element($("#lendMoneyDate"));
    });

    $("#ciOrgContractNo").change(function () {
        var ciOrgContractNo = $("#ciOrgContractNo").val();
        var orderId = $("#orderId").val();
        if($.common.isNotEmpty($.common.trim(ciOrgContractNo))){
            var reqData = {'ciOrgContractNo': ciOrgContractNo,'orderId': orderId};
            loadRemoteData("system/order/checkOrgContractNo",reqData,dealOrgContractNoIsRepeat);
        }
    });

    //收款账号选中
    $("#productAccountId").change(function () {
        var $productAccountId = $("#productAccountId");
        var productAccountId =  $.common.trim($productAccountId.val());
        if($.common.isNotEmpty(productAccountId)){
            $("#accountName").text($productAccountId.find("option:selected").attr("accountName"));
            $("#accountNum").text($productAccountId.find("option:selected").attr("accountNum"));
            $("#accountOpenBank").text($productAccountId.find("option:selected").attr("accountOpenBank"));
        }else{
            $("#accountName").text('');
            $("#accountNum").text('');
            $("#accountOpenBank").text('');
        }
    });
    var hxProductAccountId = $("#hxProductAccountId").val();
    if($.common.isNotEmpty(hxProductAccountId)){
        $("#productAccountId").val(hxProductAccountId).trigger("change");
    }
});