<script>
    import {onMount} from "svelte";
    import {DarkMode, Navbar, NavBrand, NavHamburger, NavLi, NavUl} from 'flowbite-svelte'
    import {navigating} from "$app/stores";
    import {checkSignIn} from "$lib/loginHelper.js";
    import {isAdmin, isLoggedIn} from "$lib/stores.js";

    let btnClass = "text-gray-500 dark:text-gray-400 hover:bg-gray-100 dark:hover:bg-gray-700 focus:outline-none focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 rounded-lg text-sm 5 z-50";
    let links = []

    async function generateNavLinks() {
        await checkSignIn();
        links = []
        links = [...links, {label: "Home", href: "/"}];
        if ($isLoggedIn) {
            links = [...links, {label: "Gallery", href: "/gallery"}];
            if ($isAdmin)
                links = [...links, {label: "User List", href: "/admin"}];
            links = [...links, {label: "Logout", href: "/logout"}];
        } else {
            links = [...links, {label: "Login", href: "/login"}];
        }


    }

    $: if ($navigating) generateNavLinks();

    onMount(() => {
        generateNavLinks();
    });
</script>

<Navbar color="form" let:hidden let:toggle rounded style="transition: 0.2s">
    <NavBrand href="/" style="z-index: 49">
        <img alt="Reading Pepe" class="mr-6 h-6 sm:h-9 scale-150 ml-3" src="/pepe.webp"/>
        <span class="self-center whitespace-nowrap text-4xl font-semibold dark:text-white">passec</span>
    </NavBrand>
    <NavHamburger on:click={toggle}/>
    <NavUl {hidden}>
        {#each links as link}
            <NavLi href="{link.href}">{link.label}</NavLi>
        {/each}
        <NavLi>
            <DarkMode {btnClass}/>
        </NavLi>
    </NavUl>
</Navbar>