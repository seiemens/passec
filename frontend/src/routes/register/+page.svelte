<script>
    import LoginForm from "../../components/LoginForm.svelte";
    import {createUser} from "$lib/apiCalls.js";
    import {storeAuth} from "$lib/loginHelper.js";
    import {goto} from "$app/navigation";

    async function doSignUp(username, password) {
        let res = await createUser(username, password);
        if (res.status === 200) {
            storeAuth(username, password);
            await goto("/gallery")
        } else {
        }
    }

</script>
<div class="container mx-auto w-full sm:w-2/3 my-56 outline outline-1 outline-gray-200 dark:outline-gray-700 p-10 sm:rounded-lg">
    <LoginForm buttonClickFunction={doSignUp} buttonText="Register" footerLink="/login"
               footerLinkText="Login" footerText="Already have an account?" hasPwForgottenText={false}
               headerText="Register">
    </LoginForm>
</div>
