<script>
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";
    import {isAdmin, isLoggedIn} from "$lib/stores.js";
    import {Spinner} from "flowbite-svelte";

    //Delete Cookies and Reset all Stores.
    //nested timeouts cuz else its still there somehow
    onMount(async () => {
        localStorage.clear();
        setTimeout(() => {
            isLoggedIn.update(() => false);
            isAdmin.update(() => false);
            setTimeout(() => {
                goto("/")
            }, 500)
        }, 300)


    })
</script>

<div class="text-center mt-64">
    <Spinner size={10}/>
</div>