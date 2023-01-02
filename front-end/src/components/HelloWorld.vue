<template>
	<div class="hello">
		<h1>{{ msg }} This is a microservice application !</h1>
	</div>
</template>

<script>
export default {
	name: "HelloWorld",
	props: {
		msg: String,
		name: String,
	},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style></style>
