import UIKit
import SwiftUI
import ComposeApp

struct ContentView: View {

    let viewModel: ProductViewModel = {
        let api = ApiService.Companion.shared.createDefault()
        let repo = ProductRepositoryImpl(api: api)

        let vm = ProductViewModel(
            getProducts: GetProductsUseCase(repo: repo),
            searchProducts: SearchProductsUseCase(repo: repo)
        )

        vm.loadProducts()  // IMPORTANT FOR iOS

        return vm
    }()

    var body: some View {
        ZStack {
            Color.white.ignoresSafeArea()   // Background
            ComposeViewController(viewModel: viewModel)
        }
    }
}



