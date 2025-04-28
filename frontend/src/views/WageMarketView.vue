<template>
  <div class="p-8 max-w-3xl mx-auto">
    <JobSelector @onSearch="onSearch" />

    <div v-if="loading" class="text-gray-500 italic mt-4">Loading...</div>

    <div v-else-if="error" class="text-red-600 mt-4">{{ error }}</div>

    <SalaryResults v-else :summary="summary" />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import JobSelector from '@/components/JobSelector.vue'
import SalaryResults from '@/components/SalaryResults.vue'

const summary = ref('')
const loading = ref(false)
const error = ref('')

function onSearch(title, fieldCode) {
  console.log("🔵 Title:", title, "FieldCode:", fieldCode);

  loading.value = true
  summary.value = ''
  error.value = ''

  axios.get(`/api/salary?title=${encodeURIComponent(title)}&field=${encodeURIComponent(fieldCode)}`)
      .then(res => {
        summary.value = res.data.summary
      })
      .catch(err => {
        error.value = 'Failed to fetch salary summary.'
      })
      .finally(() => {
        loading.value = false
      })
}
</script>
