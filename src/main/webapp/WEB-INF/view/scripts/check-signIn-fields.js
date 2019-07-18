$('#password, #confirm-password').on('keyup',function () {
    if($('#password').val() != $('#confirm-password').val()){
        $('#password-message').html('Пароли должны совпадать').css('color','red');
        $('#password, #confirm-password').css('border-color','red');
    }else{
        $('#password-message').html('');
        $('#password, #confirm-password').css('border-color','#ced4da');
    }
});

let regexForPhoneNumber = new RegExp('^(44|29|25|17)(\\s|-)?\\d{3}(\\s|-)?\\d{2}(\\s|-)?\\d{2}$');
let regexForCardNumber = new RegExp('^([A-Z0-9]\\s?){16}$');

function fieldControl(obj, regex) {
    if(obj.val().match(regex)){
        obj.css('border-color','#ced4da');
    }else{
        obj.css('border-color','red');
    }
}
$('#phoneNumber').on('keyup',function () {
    fieldControl($('#phoneNumber'),regexForPhoneNumber);
});
$('#cardNumber').on('keyup',function () {
    fieldControl($('#cardNumber'),regexForCardNumber);
});

