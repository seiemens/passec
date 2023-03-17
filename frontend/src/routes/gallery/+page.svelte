<script>
    import {Button, Card, DropdownItem, Input, MenuButton, Dropdown} from "flowbite-svelte";

    let filterQuery = "";
    let dropDownOpen = false;
    let num = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1];

    function deleteNote(noteId, dropdownId) {
        closeDropDown(dropdownId);
    }

    function shareNote(noteId, dropdownId) {
        closeDropDown(dropdownId);
    }

    function closeDropDown(dropdownId){
        document.getElementById("dd-mb-"+dropdownId).dispatchEvent(new Event("click"));
    }
</script>

<div class="container w-full my-24">
    <div class="flex flex-row justify-between flex-wrap">
        <h1 class="text-4xl mb-8 text-gray-700 dark:text-gray-300 mx-auto">Notes</h1>
    </div>
    <div class="flex flex-wrap gap-2 justify-center">
        {#each num as n,i}
            <Card class="cursor-pointer hover:bg-gray-50 dark:hover:bg-gray-700">
                <div class="flex justify-end">
                    <MenuButton id={"dd-mb-"+i}/>
                    <Dropdown class="w-36" >
                        <DropdownItem on:click={()=>shareNote(0,i)}>Share</DropdownItem>
                        <DropdownItem on:click={()=>deleteNote(0,i)}>Delete</DropdownItem>
                    </Dropdown>
                </div>
                <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">Noteworthy technology
                    acquisitions </h5>
                <p class="mb-3 font-normal text-gray-700 dark:text-gray-400 leading-tight">
                    Here are tf 2021 so far, in reverse chronological order.
                </p>
            </Card>
        {/each}
    </div>
</div>