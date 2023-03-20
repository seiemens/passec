<script>
    import {Hr} from "flowbite-svelte";
    import {onMount} from "svelte";
    import {getPasteById} from "$lib/apiCalls.js";
    import {page} from "$app/stores";
    import {decryptToText} from "$lib/encryptionHelper.js";

    const id = $page.params.id;

    let title = "";
    let content = "";

    onMount(async () => {
        const res = await getPasteById(id);
        title = decryptToText(id, res.title);
        content = decryptToText(id, res.content);
    });


</script>

<div class="container w-full my-24">
    <div class="flex flex-row justify-between flex-wrap">
        <h1 class="text-4xl text-gray-700 dark:text-gray-300">{title}</h1>
    </div>
    <Hr class="mb-8"/>
    <div class="flex gap-2 justify-center flex-col text-gray-700 dark:text-gray-300 whitespace-pre-line">
        {content}
    </div>
</div>