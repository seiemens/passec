<script>
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";

    //Delete Cookies and Reset all Stores.
    onMount(async ()=>{
        localStorage.clear();
        await goto("/");
    })
</script>