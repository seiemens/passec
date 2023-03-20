<script>
    import LoginForm from "../../components/LoginForm.svelte";
    import {getSelfUser} from "$lib/apiCalls.js";
    import {storeAuth} from "$lib/loginHelper.js";
    import {goto} from "$app/navigation";

    async function doLogin(username, password) {
        storeAuth(username, password);
        let res = await getSelfUser();
        if (res.status === 200) {
            await goto("/gallery")
        } else {
            localStorage.clear();
        }
    }

</script>
<div class="container mx-auto w-full sm:w-2/3 my-56 outline outline-1 outline-gray-200 dark:outline-gray-700 p-10 sm:rounded-lg">
    <LoginForm buttonClickFunction={doLogin} buttonText="Login" footerLink="/register" footerLinkText="Register"
               footerText="Not signed up yet?" hasPwForgottenText={true} headerText="Login"></LoginForm>
</div>