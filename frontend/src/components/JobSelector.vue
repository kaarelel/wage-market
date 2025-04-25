<template>
  <div class="p-4 rounded shadow-md bg-white">
    <div class="mb-3">
      <label for="jobTitle" class="block font-semibold mb-1">Job Title (optional)</label>
      <input v-model="title" id="jobTitle" type="text" class="w-full border rounded p-2" placeholder="e.g. Arendaja (optional)" />
    </div>

    <div class="mb-3">
      <label for="field" class="block font-semibold mb-1">Job Field</label>
      <select v-model="field" id="field" class="w-full border rounded p-2">
        <option disabled value="">Select a field</option>
        <option v-for="option in fields" :key="option.code" :value="option.code">
          {{ option.label }}
        </option>
      </select>
    </div>

    <div v-if="error" class="text-red-600 text-sm mb-2">{{ error }}</div>
    <button @click="emitSearch" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">Analyze</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const emit = defineEmits(['onSearch'])
const title = ref('')
const field = ref('')
const error = ref('')
const fields = ref([])

const emitSearch = () => {
  if (!field.value) {
    error.value = 'Please select a job field.'
    return
  }
  error.value = ''
  emit('onSearch', title.value, field.value)
}

onMounted(async () => {
  try {
    const response = await axios.get('/api/field-codes')
    fields.value = Object.entries(response.data).map(([label, code]) => ({ label, code }))
  } catch (e) {
    error.value = 'Failed to load fields.'
  }
})
</script>
