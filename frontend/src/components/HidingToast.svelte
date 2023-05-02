<script>
    import {Toast} from "flowbite-svelte";
    import {slide} from "svelte/transition";

    export let showToast = false;
    export let isErrorToast = false;

    $: toastColor = isErrorToast ? "red" : "blue";

    $: if (showToast === true) {
        setTimeout(() => {
            showToast = false;
        }, 5000);
    }
</script>

<Toast bind:open={showToast} class="fixed bottom-6 left-6" bind:color={toastColor} simple transition={slide}>
    <svelte:fragment slot="icon">
        {#if isErrorToast}
            <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20"
                 xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd"
                      d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z"
                      clip-rule="evenodd">
                </path>
            </svg>
        {:else}
            <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="1.5" viewBox="0 0 24 24"
                 xmlns="http://www.w3.org/2000/svg">
                <path d="M9 12.75L11.25 15 15 9.75M21 12a9 9 0 11-18 0 9 9 0 0118 0z" stroke-linecap="round"
                      stroke-linejoin="round"/>
            </svg>
        {/if}
    </svelte:fragment>
    <slot></slot>
</Toast>