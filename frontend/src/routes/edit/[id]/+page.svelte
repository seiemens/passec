<script>
    import {Button, Input, Label, Modal, P, Textarea, Toast} from "flowbite-svelte";
    import {onMount} from "svelte";
    import {deletePaste, editPaste, getPasteById} from "$lib/apiCalls.js";
    import {page} from "$app/stores";
    import CryptoES from "crypto-es";
    import {decryptToText, getEncryptionKey} from "$lib/encryptionHelper.js";
    import {beforeNavigate} from "$app/navigation";
    import {slide} from "svelte/transition";
    import HidingToast from "../../../components/HidingToast.svelte";

    const id = $page.params.id;

    let title = "";
    let content = "";
    let encryptionKey = "";
    let encryptionKeyModal = false;
    let showToast = false;
    let toastText = "";
    let toastIsError = false;

    onMount(async () => {
        encryptionKey = getEncryptionKey(id);
        const res = await getPasteById(id);
        title = decryptToText(id, res.title);
        content = decryptToText(id, res.content);
    });

    async function saveNote() {
        const encTitle = CryptoES.AES.encrypt(title, encryptionKey).toString();
        const encContent = CryptoES.AES.encrypt(content, encryptionKey).toString();
        const res = await editPaste(id, encTitle, encContent);
        if (res.status === 200) {
            toastText="Note Saved!";
            showToast = true;
            toastIsError = false;
        }else{
            toastText="Could not save Note!";
            showToast = true;
            toastIsError = true;
        }
    }

    function copyEncryptionKey() {
        navigator.clipboard.writeText(encryptionKey);
        toastText="Link Copied to clipboard!";
        showToast = true;
        toastIsError = false;
    }

    function generateUrlExample() {
        let domain = "https://" + window.location.hostname;
        let path = "/view/" + id;
        let parameter = "?k=" + encryptionKey;

        return domain + path + parameter;
    }

    async function cancelEdit() {
        history.back();
    }

</script>

<div class="container w-full my-24">
    <div class="flex flex-row justify-between flex-wrap">
        <h1 class="text-4xl mb-8 text-gray-700 dark:text-gray-300 mx-auto">Edit Note</h1>
    </div>
    <div class="flex gap-2 justify-center flex-col">
        <Label class="space-y-2 mb-5">
            <span>Title</span>
            <div class="flex flex-row gap-2">
                <Input bind:value={title} class="flex-auto" placeholder="note title" size="md" type="text"/>
                <Button class="flex-auto w-96" on:click={()=>encryptionKeyModal=true}>View Encryption Key</Button>
            </div>
        </Label>
        <Label class="space-y-2 mb-5">
            <span>Content</span>
            <Textarea bind:value={content} class="h-96" placeholder="note content"></Textarea>
        </Label>
        <div class="flex flex-row gap-2">
            <Button class="mt-5 w-20 ml-auto" color="dark" on:click={()=>cancelEdit()} outline>Cancel</Button>
            <Button class="mt-5 w-80 " on:click={()=>saveNote()}>Save</Button>
        </div>
    </div>
</div>

<Modal bind:open={encryptionKeyModal} title="View Encryption Key">
    <div>
        <P class="p-2 dark:bg-gray-900 bg-gray-100 mb-4">{encryptionKey}</P>
        <P class="text-lg"><b>How to Use</b></P>
        <P>Append ?k=[key] to the view url. Example:</P>
        <P class="p-2 dark:bg-gray-900 bg-gray-100 mb-4">{generateUrlExample()}</P>
    </div>
    <svelte:fragment slot='footer'>
        <Button class="ml-auto" color="dark" on:click={()=>encryptionKeyModal=false} outline>Close</Button>
        <Button on:click={()=>copyEncryptionKey()}>Copy Key</Button>
    </svelte:fragment>
</Modal>

<HidingToast isErrorToast={toastIsError} bind:showToast={showToast}>
    {toastText}
</HidingToast>