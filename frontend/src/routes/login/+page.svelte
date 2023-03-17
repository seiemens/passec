<script>
    import {A, Alert, Button, ButtonGroup, Hr, Input, InputAddon, Label, P, Tooltip} from "flowbite-svelte";
    import LoginForm from "../../components/LoginForm.svelte";

    let showPw = false;
    let username = "";
    let password = "";
    let alertHidden = "hidden"

    function doLogin() {
    }

</script>
<div class="container mx-auto w-full sm:w-2/3 my-56 outline outline-1 outline-gray-200 dark:outline-gray-700 p-10 sm:rounded-lg">
    <LoginForm buttonClickFunction={()=>doLogin()} headerText="Login" hasPwForgottenText={true} buttonText="Login" footerText="Not signed up yet?" footerLink="/register" footerLinkText="Register"></LoginForm>
    <Alert color="red" class="mt-4 {alertHidden}">
        <span class="font-medium">Login Error!</span> Username or Password is wrong!
    </Alert>
</div>