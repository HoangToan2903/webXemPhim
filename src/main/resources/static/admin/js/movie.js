function Validator(options) {

    function getPerent(element, selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    }

    var selectorRules = {};

    // hàm thuc hien validate
    function validate(inputElement, rule) {



        var errorElement = getPerent(inputElement, options.formGroup).querySelector(options.errorSelect);
        var error = rule.test(inputElement.value);


        // lấy ra các rule của slector
        // var rules = selectorRules[rule.selector];
        //
        // // kiểm tra
        // for (var i = 0; i < rules.length; i++) {
        //     switch (inputElement.type) {
        //         case 'radio':
        //         case 'checkbox':
        //             error = rules[i](
        //                 formElement.querySelector(rule.selector + ':checked')
        //             );
        //
        //             break;
        //         default:
        //             error = rules[i](inputElement.value);
        //
        //     }
        //
        //     if (error) break;
        // }


        if (error) {
            errorElement.innerText = error;
            getPerent(inputElement, options.formGroup).classList.add('invalid');
        } else {
            errorElement.innerText = '';
            getPerent(inputElement, options.formGroup).classList.remove('invalid');

        }
        return !error;

    }


    var formElement = document.querySelector(options.form);

    if (formElement) {

        var isFormVlaid = true;

        formElement.onsubmit = function (e) {
            e.preventDefault();
            // lặp qua từng rule
            options.rules.forEach(function (rule) {
                var inputElement = formElement.querySelector(rule.selector);
                var isValid = validate(inputElement, rule);
                if (!isValid) {
                    isFormVlaid = false;
                }
            });

            if (isFormVlaid) {
                // Nếu có onSubmit là một function
                // Trường hợp submit với javascript
                if (typeof options.onSubmit === 'function') {
                    // Lấy ra tất cả input có Atribute là name
                    var enableInputs = formElement.querySelectorAll('[name]');

                    // vÌ enableInputs là một NodeList mà NodeList lại không sử dụng được reduce
                    // Chuyển nó thành Array để sử dụng được reduce
                    var formValue = Array.from(enableInputs).reduce((values, input) => {
                        // Reduce thành key:value vào object sau đó cuối cùng là return ra values là một object
                        switch (input.type) {

                            default:
                                values[input.name] = input.value;
                        }
                        return values;
                    }, {});

                    // Callback lại và truyền vào dữ liệu
                    options.onSubmit(formValue);
                } else {
                    // Trường hợp submit với hành vi mặc định
                    formElement.submit();
                }
            }
        }


        options.rules.forEach(function (rule) {


            // lưu lại cá rule
            // selectorRules[rule.selector] = rule.test;

            if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test);
            } else {
                selectorRules[rule.selector] = [rule.test]
            }

            var inputElements = formElement.querySelectorAll(rule.selector);

            Array.from(inputElements).forEach(function (inputElement) {
                if (inputElement) {
                    inputElement.onblur = function () {
                        validate(inputElement, rule);

                    }
                    inputElement.oninput = function () {
                        var errorElement = getPerent(inputElement, options.formGroup).querySelector(options.errorSelect);
                        errorElement.innerText = '';
                        getPerent(inputElement, options.formGroup).classList.remove('invalid');
                    }
                }

            })



        });
    }
}

Validator.isRequired = function (selector, message) {
    return {
        selector: selector,
        test: function (value) {
            return value ? undefined : message || 'Vui lòng nhập trường hợp này'
        }
    };
}


