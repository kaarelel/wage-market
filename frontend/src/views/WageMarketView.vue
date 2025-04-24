<template>
  <div>
    <h1 class="mb-4">Wage Market Analyzer</h1>
    <JobSelector @job-selected="analyzeJob" />
    <SalaryResults :summary="aiSummary" />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import JobSelector from '../components/JobSelector.vue'
import SalaryResults from '../components/SalaryResults.vue'

const aiSummary = ref('')

async function analyzeJob(job) {
  const res = await fetch(`/api/salary?title=${encodeURIComponent(job)}`)
  const json = await res.json()
  aiSummary.value = json.summary
}
</script>