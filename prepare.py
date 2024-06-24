import os
import shutil

build_dir = 'build'
if not os.path.exists(build_dir):
    os.makedirs(build_dir)

shutil.copy('config/config.json', build_dir)