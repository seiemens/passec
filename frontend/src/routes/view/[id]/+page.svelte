<script>
    import {Button, Hr, Input, Label, Modal} from "flowbite-svelte";
    import {onMount} from "svelte";
    import {getPasteById} from "$lib/apiCalls.js";
    import {page} from "$app/stores";
    import {decryptToTextWithKey} from "$lib/encryptionHelper.js";

    const id = $page.params.id;
    let encryptionKey = $page.url.searchParams.get("k");
    let noKeyPresent = encryptionKey === null;

    let title = "encryption key not present";
    let content = "encryption key not present";

    onMount(async () => {
        await loadPaste();
    });

    async function loadPaste() {
        const res = await getPasteById(id);
        title = decryptToTextWithKey(encryptionKey, res.title);
        content = decryptToTextWithKey(encryptionKey, res.content);
    }


</script>

<div class="container w-full my-24">
    <div class="flex flex-row justify-between flex-wrap">
        <h1 class="text-4xl text-gray-700 dark:text-gray-300">{title}</h1>
    </div>
    <Hr class="mb-8"/>
    <div class="flex gap-2 justify-center flex-col text-gray-700 dark:text-gray-300 whitespace-pre-line">
        <pre>{content}</pre>
    </div>
</div>

<Modal autoclose bind:open={noKeyPresent} size="xl" title="Please Specify an encryption key!">
    <div class="w-96">
        <Label class="space-y-2 mb-5">
            <span>Encryption Key</span>
            <Input bind:value={encryptionKey} size="md" type="text"/>
        </Label>
    </div>
    <svelte:fragment slot='footer'>
        <Button class="ml-auto" on:click={()=>loadPaste()}>Submit Key</Button>
    </svelte:fragment>
</Modal>

<style>
    pre {
        -webkit-text-size-adjust: 100%;
        -moz-tab-size: 4;
        tab-size: 4;
        font-family: ui-sans-serif, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
        font-feature-settings: normal;
    }
</style>