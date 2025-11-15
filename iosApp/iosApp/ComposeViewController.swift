//
// Created by Neeraj Vaishnav on 15/11/25.
//

import Foundation

import SwiftUI
import ComposeApp

struct ComposeViewController: UIViewControllerRepresentable {

    let viewModel: ProductViewModel

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(viewModel: viewModel)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
