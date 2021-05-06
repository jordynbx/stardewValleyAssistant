<%--        Display a card deck showing details about the searched-for item--%>
<c:if test="${isCrop}">
    <div class="card-deck my-cards">

        <div class="card text-white bg-success mb-3 my-card" style="max-width: 20rem;">
            <div class="card-body">
                <h4 class="card-title">Seeds</h4>
                <p class="card-text">
                    Purchase price: ${crop.seedPrice}<br>
                    Sell price: ${crop.sellPrice}<br>
                </p>
            </div>
        </div>

        <div class="card text-white bg-warning mb-3 my-card" style="max-width: 20rem;">
            <div class="card-body">
                <h4 class="card-title">Seasons</h4>
                <p class="card-text">${crop.season}</p>
            </div>
        </div>
    </div>

    <div class="card-deck my-cards">

        <div class="card text-white bg-info mb-3 my-card" style="max-width: 20rem;">
            <div class="card-body">
                <h4 class="card-title">Bundles</h4>
                <p class="card-text">${crop.bundles}</p>
            </div>
        </div>

        <div class="card text-white bg-danger mb-3 my-card" style="max-width: 20rem;">
            <div class="card-body">
                <h4 class="card-title">Recipes</h4>
                <p class="card-text">${crop.recipes}</p>
            </div>
        </div>
    </div>
</c:if>