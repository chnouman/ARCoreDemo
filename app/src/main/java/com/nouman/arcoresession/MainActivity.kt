package com.nouman.arcoresession

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment

        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val createAnchor = hitResult.createAnchor()

            var anchorNode = AnchorNode(createAnchor)


            ModelRenderable.builder().setSource(this, Uri.parse("55566.sfb"))
                .build()
                .thenAccept {

                    var tranformableNode = TransformableNode(arFragment.transformationSystem)

                    tranformableNode.renderable = it
                    tranformableNode.setParent(anchorNode)
                    anchorNode.setParent(arFragment.arSceneView.scene)
                    tranformableNode.select()

                }
                .exceptionally {

                    return@exceptionally null
                }


        }
    }
}
