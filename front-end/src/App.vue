<template>
	<div>
		<HelloWorld msg="Hello there !" />
		<hr />
        <div class="prod" v-for="p in products" v-bind:key="p.id">
            <ProductsComponent :productName="p.name" :productPrice="p.price" :productDesc="p.description"/>
        </div>
	</div>
</template>

<script>
import HelloWorld from "./components/HelloWorld.vue";
import ProductsComponent from "./components/ProductsComponent.vue"

export default {
	name: "App",
	components: {
		HelloWorld,
        ProductsComponent
	},

    data() {
		return {
			products: null,
		};
	},

	async created() {
		// const resource = "https://api.npms.io/v2/search?q=vue";
		const resource = "http://localhost:8082/api/product/list";
		const options = {
			method: "GET",
		};

		// GET request using fetch with async/await
		this.products = await fetch(resource, options).then(function (res) {
			return res.json();
		});
	},
};
</script>

<style>
#app {
	font-family: Avenir, Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
	margin-top: 60px;
}

.prod {
    padding-bottom: 16px;
}
</style>
