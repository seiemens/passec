<script>
    import {
        Button,
        TableBody,
        TableBodyCell,
        TableBodyRow,
        TableHead,
        TableHeadCell,
        TableSearch
    } from "flowbite-svelte";
    import {onMount} from "svelte";
    import {deleteUser, getAllUsers} from "$lib/apiCalls.js";

    let searchTerm = '';
    let users = [];

    onMount(async () => {
        users = await getAllUsers();
    })

    $: filteredUsers = users.filter(
        (user) => user.username.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1
    );

    async function doDelete(id) {
        const res = await deleteUser(id)
        if (res.status !== 200)
            return;
        users = users.filter((x) => x.id !== id);
        searchTerm += " ";
        searchTerm = searchTerm.substring(0, searchTerm.length - 2)
    }
</script>

<div class="container w-full my-24">

    <div class="flex flex-row justify-between flex-wrap">
        <h1 class="text-4xl mb-8 text-gray-700 dark:text-gray-300 mx-auto">User List</h1>
    </div>
    <TableSearch bind:inputValue={searchTerm} hoverable={true} placeholder="Search by username">
        <TableHead>
            <TableHeadCell>ID</TableHeadCell>
            <TableHeadCell>Username</TableHeadCell>
            <TableHeadCell>Is Admin</TableHeadCell>
            <TableHeadCell>Action</TableHeadCell>
        </TableHead>
        <TableBody class="divide-y">
            {#each filteredUsers as user}
                <TableBodyRow>
                    <TableBodyCell>{user.id}</TableBodyCell>
                    <TableBodyCell>{user.username}</TableBodyCell>
                    <TableBodyCell>{user.admin}</TableBodyCell>
                    <TableBodyCell>
                        <Button on:click={()=>doDelete(user.id)}>Delete</Button>
                    </TableBodyCell>
                </TableBodyRow>
            {/each}
        </TableBody>
    </TableSearch>
</div>