<script>
    import {Heading, P, Button} from 'flowbite-svelte'
</script>

<div class="text-center container mx-auto w-full sm:w-2/3 sm:my-56 p-10">
    <Heading tag="h1" class="mb-4" customSize="text-4xl font-extrabold  md:text-5xl lg:text-6xl">Securely store notes!
    </Heading>
    <P class="mb-6 text-lg lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">We at <span
            class="font-semibold dark:text-blue-500 text-blue-600">passec</span> believe in privacy. That's why we
        securely encrypt all notes stored on this page with <span
                class="font-semibold dark:text-blue-500 text-blue-600">military grade encryption!</span></P>
    <Button href="/register">Register Now
        <svg class="ml-2 -mr-1 w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd"
                  d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z"
                  clip-rule="evenodd"></path>
        </svg>
    </Button>
</div>