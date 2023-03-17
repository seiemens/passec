<script>
    import {Button, Input, Label, Textarea} from "flowbite-svelte";

    let title = "[note title]";
    let content = "[note content Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad assumenda distinctio, ducimus eligendi et labore nam nemo, numquam quo quod repellat unde velit voluptatibus? Accusamus exercitationem nemo sequi veniam voluptas!]";

    function saveNote() {

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
                <Input bind:value={title} placeholder="johnny" size="md" type="text" class="flex-auto"/>
                <Button class="flex-auto w-96" >View Encryption Key</Button>
            </div>
        </Label>
        <Label class="space-y-2 mb-5">
            <span>Content</span>
            <Textarea bind:value={content} class="h-96"></Textarea>
        </Label>
        <div class="flex flex-row justify-between flex-wrap">
            <Button class="mt-5 w-96 ml-auto" on:click={()=>saveNote()}>Save</Button>
        </div>
    </div>
</div>