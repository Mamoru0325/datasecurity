<template>
    <div class="pagination">
        <button @click="goToPage(1)" :disabled="currentPage === 1">First</button>
        <button @click="goToPage(currentPage - 1)" :disabled="currentPage === 1">Previous</button>
        <span>Page {{ currentPage }} of {{ totalPages }}</span>
        <button @click="goToPage(currentPage + 1)" :disabled="currentPage >= totalPages">Next</button>
        <button @click="goToPage(totalPages)" :disabled="currentPage >= totalPages">Last</button>
    </div>
</template>
  
<script>
export default {
    name:'PaginationView',
    props: {
        currentPage: Number,
        totalPages: Number
    },
    methods: {
        goToPage(page) {
            if (page >= 1 && page <= this.totalPages) {
                this.$emit('page-changed', page);
            }
        }
    }
};
</script>
<style scoped>
.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.pagination button {
  background-color: #3498db;
  color: #fff;
  border: none;
  padding: 8px 16px;
  margin: 0 4px;
  cursor: pointer;
  border-radius: 4px;
}

.pagination button:hover {
  background-color: #2980b9;
}

.pagination button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.pagination span {
  margin: 0 10px;
}

/* Optional: Add additional styling for active page */

.pagination button.active {
  background-color: #e74c3c;
}

.pagination button.active:hover {
  background-color: #c0392b;
}
</style>