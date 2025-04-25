<template>
  <div class="bg-white shadow-md rounded p-4 text-gray-800 border mt-4">
    <p class="whitespace-pre-line leading-relaxed">{{ summary }}</p>
  </div>
</template>

<script setup>
defineProps(['summary'])
</script>
