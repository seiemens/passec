<script>
    import {A, Alert, Button, ButtonGroup, Hr, Input, InputAddon, Label, P, Tooltip} from "flowbite-svelte";

    export let buttonClickFunction;
    export let headerText;
    export let hasPwForgottenText;
    export let buttonText;
    export let footerText;
    export let footerLink;
    export let footerLinkText;

    let showPw = false;
    let username = "";
    let password = "";

</script>
<div>
    <h1 class="text-4xl text-center mb-8 text-gray-700 dark:text-gray-300">{headerText}</h1>
    <Label class="space-y-2 mb-5">
        <span>Username</span>
        <Input bind:value={username} placeholder="johnny" size="md" type="text"/>
    </Label>
    <Label class="mb-2" for="show-password">Your password</Label>
    <ButtonGroup class="w-full">
        <InputAddon>
            <button on:click={() => (showPw = !showPw)}>
                {#if showPw}
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" class="w-5 h-5">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M2.036 12.322a1.012 1.012 0 010-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178z"/>
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"/>
                    </svg>
                {:else}
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" class="w-5 h-5">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M3.98 8.223A10.477 10.477 0 001.934 12C3.226 16.338 7.244 19.5 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.45 10.45 0 0112 4.5c4.756 0 8.773 3.162 10.065 7.498a10.523 10.523 0 01-4.293 5.774M6.228 6.228L3 3m3.228 3.228l3.65 3.65m7.894 7.894L21 21m-3.228-3.228l-3.65-3.65m0 0a3 3 0 10-4.243-4.243m4.242 4.242L9.88 9.88"/>
                    </svg>
                {/if}
            </button>
        </InputAddon>
        <Input bind:value={password} id="show-password"
               placeholder="{showPw ? 'passw0rd' : '********'}" type={showPw ? 'text' : 'password'}
               on:keydown={(e)=>{if(e.keyCode == 13)buttonClickFunction(username.toLowerCase(),password);}}/>
    </ButtonGroup>
    {#if hasPwForgottenText}
        <a class="mb-2 dark:text-gray-600 text-sm font-medium" id="pw-forgor">Password forgotten?</a>
        <Tooltip bottom triggeredBy="#pw-forgor">Unfortunately due to security, you cannot reset your Password!
        </Tooltip>
    {/if}
    <div class="flex flex-row justify-center">
        <Button class="mt-5 w-96"
                on:click={()=>buttonClickFunction(username.toLowerCase(),password)}>{buttonText}</Button>
    </div>
    <Hr class="w-1/2"></Hr>
    <div class="flex flex-row justify-center">
        <P weight="light" color="text-gray-500 dark:text-gray-400">
            {footerText + " "} <A href={footerLink}>{footerLinkText}</A>
        </P>
    </div>
</div>