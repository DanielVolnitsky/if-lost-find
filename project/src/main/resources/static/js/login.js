$(document).ready(function () {
    $('.ui.form')
        .form({
            fields: {
                name: {
                    identifier: 'name',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please enter your name',
                    }],
                },
                surname: {
                    identifier: 'surname',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please enter your surname',
                    }],
                },
                email: {
                    identifier: 'email',
                    rules: [{
                            type: 'empty',
                            prompt: 'Please enter your e-mail'
                        },
                        {
                            type: 'email',
                            prompt: 'Please enter a valid e-mail'
                        }
                    ]
                },
                country: {
                    identifier: 'country',
                    rules: [{
                        type: 'empty',
                        prompt: 'Country field is blank',
                    }],
                },
                city: {
                    identifier: 'city',
                    rules: [{
                        type: 'empty',
                        prompt: 'City field is blank',
                    }]
                },
                password: {
                    identifier: 'password',
                    rules: [{
                            type: 'empty',
                            prompt: 'Please enter your password'
                        },
                        {
                            type: 'length[6]',
                            prompt: 'Your password must be at least 6 characters'
                        }
                    ]
                }
            }
        });
});
