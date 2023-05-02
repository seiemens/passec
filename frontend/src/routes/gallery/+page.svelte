<script>
    import {Button, Card, Dropdown, DropdownItem, MenuButton, Spinner, Toast, Tooltip} from "flowbite-svelte";
    import {goto} from "$app/navigation";
    import {onMount} from "svelte";
    import {createPaste, deletePaste, getPastes} from "$lib/apiCalls.js";
    import {decryptToText, getEncryptionKey} from "$lib/encryptionHelper.js";
    import HidingToast from "../../components/HidingToast.svelte";
    import {isAdmin, isLoggedIn} from "$lib/stores.js";

    let loading = true;
    let showCopyToast = false;

    let notes = [];

    onMount(async () => {
            if(!$isLoggedIn)
                await goto("/login");

            notes = await getPastes();

            for (let note of notes) {
                if (note.title === "" && note.content === "") {
                    await deletePaste(note.id);
                    notes = notes.filter((n) => n.id !== note.id);
                }
            }

            loading = false;
        }
    )

    async function deleteNote(noteId, dropdownId) {
        const res = await deletePaste(noteId);
        if (res.status !== 200)
            return;
        notes = notes.filter((x) => x.id !== noteId);
        closeDropDown(dropdownId);
    }

    function shareNote(noteId, dropdownId) {
        closeDropDown(dropdownId);

        let domain = "https://" + window.location.hostname;
        let path = "/view/" + noteId;
        let parameter = "?k=" + getEncryptionKey(noteId);
        navigator.clipboard.writeText(domain + path + parameter);

        showCopyToast = true;
    }

    function closeDropDown(dropdownId) {
        document.getElementById("dd-mb-" + dropdownId).dispatchEvent(new Event("click"));
    }

    async function createNote() {
        const res = await createPaste("", "")
        await goto("/edit/" + res.id);
    }
</script>

<div class="container w-full my-24">
    <div class="flex flex-row justify-between flex-wrap">
        <h1 class="text-4xl mb-8 text-gray-700 dark:text-gray-300 mx-auto">Notes</h1>
    </div>
    <div class="flex flex-wrap gap-2 justify-center">
        {#if loading}
            <div class="text-center">
                <Spinner size={10}/>
            </div>
        {:else}
            {#each notes as note,i}
                <Card class="cursor-pointer hover:bg-gray-50 dark:hover:bg-gray-700"
                      href={"/view/"+note.id+"?k="+getEncryptionKey(note.id)}>
                    <div class="flex justify-end">
                        <MenuButton id={"dd-mb-"+i} on:click={(e)=>e.preventDefault()}/>
                        <Dropdown class="w-36">
                            <DropdownItem on:click={(e)=>{e.preventDefault();goto("/edit/"+note.id)}}>Edit
                            </DropdownItem>
                            <DropdownItem on:click={(e)=>{e.preventDefault();shareNote(note.id,i);}}>Share
                            </DropdownItem>
                            <DropdownItem on:click={(e)=>{e.preventDefault();deleteNote(note.id,i);}}>Delete
                            </DropdownItem>
                        </Dropdown>
                    </div>
                    <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white line break-all">{decryptToText(note.id, note.title).slice(0, 40)}</h5>
                    <p class="mb-3 font-normal text-gray-700 dark:text-gray-400 leading-tight break-all">{decryptToText(note.id, note.content).slice(0, 200)}</p>
                </Card>
            {/each}
        {/if}
    </div>
</div>
<Button class="!p-2 fixed right-6 bottom-6" color="blue" gradient on:click={()=>createNote()} pill={true} size="xl">
    <svg aria-hidden="true" class="w-8 h-8 transition-transform group-hover:rotate-45" fill="none" stroke="currentColor"
         viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path d="M12 6v6m0 0v6m0-6h6m-6 0H6" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    </svg>
</Button>
<Tooltip>Create new Note</Tooltip>

<HidingToast isErrorToast={false} bind:showToast={showCopyToast}>
    Link Copied to clipboard!
</HidingToast>