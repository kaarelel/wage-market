<template>
  <div class="mb-4">
    <label for="job" class="form-label">Select Job/Field:</label>
    <select v-model="selected" class="form-select" id="job">
      <option disabled value="">-- Choose --</option>
      <option>Software Developer</option>
      <option>Data Analyst</option>
      <option>Project Manager</option>
      <option>Healthcare Specialist</option>
    </select>
    <button class="btn btn-primary mt-3" @click="emitSelection">Analyze</button>
  </div>
</template>

<script setup>
import { ref, defineEmits } from 'vue'

const selected = ref('')
const emit = defineEmits(['job-selected'])

function emitSelection() {
  if (selected.value) emit('job-selected', selected.value)
}
</script>