<script>
    import {onMount} from "svelte";
    import {DarkMode, Navbar, NavBrand, NavHamburger, NavLi, NavUl} from 'flowbite-svelte'
    import {navigating} from "$app/stores";

    let btnClass = "text-gray-500 dark:text-gray-400 hover:bg-gray-100 dark:hover:bg-gray-700 focus:outline-none focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 rounded-lg text-sm 5 z-50";
    let links = []

    async function generateNavLinks() {
        links = []
        links = [...links, {label: "Home", href: "/"}];
        //order is important, else it looks ugly and makes no sense from ergonomics perspective
        links = [...links, {label: "Gallery", href: "/gallery"}];
        links = [...links, {label: "Edit", href: "/edit/bla"}];

        links = [...links, {label: "User List", href: "/admin"}];

        links = [...links, {label: "Logout", href: "/logout"}];
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