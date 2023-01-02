<template>
	<div class="card" style="width: 18rem">
		<div class="card-body">
			<h5 class="card-title">{{ productName }}</h5>
			<h6 class="card-subtitle mb-2 text-muted">Price: {{ productPrice }} €</h6>
			<p class="card-text">Description: {{ productDesc }}</p>
			<button
				type="button"
				class="btn btn-outline-success"
				@click="placeOrder(productName, productPrice)"
			>
				Order
			</button>
		</div>
		<span>{{ orderStatus }}</span>
	</div>
</template>

<script>
export default {
	name: "ProductsComponent",
	props: {
		productName: String,
		productPrice: String,
		productDesc: String,
	},
	data() {
		return {
			orderStatus: null,
			productStatus: null,
		};
	},
	methods: {
		async placeOrder() {
			const body = JSON.stringify({
				name: "iphone_15",
				description: "personal",
				price: "600",
			});

			const options = {
				method: "POST",
				body: body,
			};
			const resource = "http://localhost:8082/api/product/add";

			let result = await fetch(resource, options).then((res) => {
				console.log(res);
			});
			
			console.log(options);
			console.log(typeof options.body);

			this.orderStatus = result;
		},
	},
};
</script>

<style scoped>
.card {
	margin: auto;
}
</style>
