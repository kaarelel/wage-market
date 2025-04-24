<template>
  <div v-if="summary" class="alert alert-info">
    <h5 class="mb-2">Salary Trend Summary</h5>
    <p>{{ summary }}</p>
  </div>
</template>

<script setup>
const props = defineProps({
  summary: String
})
</script>